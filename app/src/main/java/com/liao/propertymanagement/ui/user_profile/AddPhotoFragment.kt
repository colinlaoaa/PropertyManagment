package com.liao.propertymanagement.ui.user_profile

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import com.liao.propertymanagement.R
import com.liao.propertymanagement.view_model.user_profile.UserProfileViewModel
import kotlinx.android.synthetic.main.fragment_bottom_sheet_dialog_camera.*
import java.io.ByteArrayOutputStream
import java.io.File

class AddPhotoFragment(private var viewModel: UserProfileViewModel) :
    BottomSheetDialogFragment() {

    private var fragmentView: View? = null
    private val CAMERA_REQUEST_CODE = 101
    private val CHOICE_FROM_ALBUM_REQUEST_CODE = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView =
            inflater.inflate(R.layout.fragment_bottom_sheet_dialog_camera, container, false)
        return fragmentView
    }

    private fun init() {
        item_camera.setOnClickListener {
            onMultiplePermission()
        }
        item_gallery.setOnClickListener {
            onSinglePermission()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun onSinglePermission() {
        Dexter.withActivity(activity)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    choiceFromAlbum()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    if (response!!.isPermanentlyDenied) {
                        Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT)
                            .show()
                        showMyDialog()
                    }
                }
            }).check()
    }

    private fun onMultiplePermission() {
        Dexter.withActivity(activity).withPermissions(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                if (report!!.areAllPermissionsGranted()) {
                    Toast.makeText(
                        context,
                        "All permission granted",
                        Toast.LENGTH_SHORT
                    ).show()
                    openCamera()
                }

                if (report.isAnyPermissionPermanentlyDenied) {
                    Toast.makeText(
                        context,
                        "permission denied permanently",
                        Toast.LENGTH_SHORT
                    ).show()
                    showMyDialog()
                }
                if (report.deniedPermissionResponses.isNotEmpty()) {
                    val listOfDeniedResponse = report.deniedPermissionResponses
                    var flag = false
                    for (item in listOfDeniedResponse) {
                        if (item.permissionName == "android.permission.CAMERA") {
                            flag = true
                        }
                    }
                    if (!flag) {
                        openCamera()
                    }
                }

            }

            override fun onPermissionRationaleShouldBeShown(
                permission: MutableList<PermissionRequest>?,
                token: PermissionToken?
            ) {
                token!!.continuePermissionRequest()

            }
        }).onSameThread().check()
    }

    private fun showMyDialog() {
        var builder = AlertDialog.Builder(this.requireContext())
        builder.setTitle("Need U Permission")
        builder.setMessage("please give permission")
        builder.setPositiveButton("Go to Settings", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, p1: Int) {
                dialog?.dismiss()
                openSettings()
            }
        })

        builder.setNegativeButton("Cancel")
        { dialog, p1 -> dialog.dismiss() }
        builder.show()
    }

    private fun openSettings() {
        val uri = Uri.Builder()
            .scheme("package")
            .opaquePart(activity?.packageName)
            .build()
        var myIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, uri)
        startActivityForResult(myIntent, CAMERA_REQUEST_CODE)
    }

    private fun choiceFromAlbum() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            startActivityForResult(
                Intent(Intent.ACTION_GET_CONTENT).setType("image/*"),
                CHOICE_FROM_ALBUM_REQUEST_CODE
            )
        } else {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, CHOICE_FROM_ALBUM_REQUEST_CODE)
        }

    }


    private fun openCamera() {
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            var thumbnail: Bitmap = data?.extras?.get("data") as Bitmap
            val tempUri: Uri? = getImageUri(this.requireContext(), thumbnail)
            val finalFile = File(getRealPathFromURI(tempUri))
            Log.d("imageLink",finalFile.absolutePath)
            viewModel.transferBitmap(thumbnail,finalFile.absolutePath)
        }

        if (requestCode == CHOICE_FROM_ALBUM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val selectedPhotoUri = data.data
                try {
                    selectedPhotoUri?.let {
                        if (Build.VERSION.SDK_INT < 28) {
                            var bitmap: Bitmap =
                                MediaStore.Images.Media.getBitmap(activity?.contentResolver, selectedPhotoUri)

                            var uri = getImageUri(this.requireContext(), bitmap)
                            var path = getRealPathFromURI(uri)
                            viewModel.transferBitmap(bitmap,path!!)
                        } else {
                            val source = ImageDecoder.createSource(activity?.contentResolver!!, selectedPhotoUri)
                            val bitmap = ImageDecoder.decodeBitmap(source)
                            var uri = getImageUri(this.requireContext(), bitmap)
                            var path = getRealPathFromURI(uri)
                            viewModel.transferBitmap(bitmap,path!!)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    // get URI from bitmap
    private fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path: String =
            MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

    // get actual path
    private fun getRealPathFromURI(uri: Uri?): String? {
        val cursor: Cursor? = activity?.contentResolver?.query(uri!!, null, null, null, null)
        cursor!!.moveToFirst()
        val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        return cursor.getString(idx)
    }


}