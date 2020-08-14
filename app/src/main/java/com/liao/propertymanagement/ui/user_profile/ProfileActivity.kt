package com.liao.propertymanagement.ui.user_profile

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.liao.propertymanagement.R
import com.liao.propertymanagement.databinding.ActivityProfileBinding
import com.liao.propertymanagement.helper.toolbar
import com.liao.propertymanagement.view_model.user_profile.UserProfileViewModel
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    lateinit var viewModel: UserProfileViewModel
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        toolbar("Profile")
        binding.viewModel = viewModel

        init()
    }

    private fun init() {
        bottomSheetBehavior = BottomSheetBehavior.from<ConstraintLayout>(bottomSheet2)
        binding.buttonEditProfile.setImageResource(R.drawable.ic_baseline_add_24)
        binding.buttonEditProfile.setOnClickListener {
            viewModel.setContent()
            slideUpBottomSheet()
        }




        setProfile()



    }

    private fun setProfile() {
        circularImageView.setImageResource(R.drawable.ic_landlord)
        fancyAboutPage.setCover(R.drawable.bg); //Pass your cover image
        fancyAboutPage.setName(viewModel.getUserName());
        fancyAboutPage.setDescription(viewModel.getUserType() + "\n"+viewModel.getUserEmail());
        fancyAboutPage.setAppIcon(R.drawable.collect_rent); //Pass your app icon image
        fancyAboutPage.setAppName("Property Management");
        fancyAboutPage.setVersionNameAsAppSubTitle("1.2.3");
        fancyAboutPage.setAppDescription(viewModel.getDes());
        fancyAboutPage.addEmailLink(viewModel.getUserEmail());     //Add your email id
        fancyAboutPage.addFacebookLink(viewModel.getfb());  //Add your facebook address url
        fancyAboutPage.addTwitterLink(viewModel.getTwitter());
        fancyAboutPage.addLinkedinLink(viewModel.getLinkedIn());
        fancyAboutPage.addGitHubLink(viewModel.getGit());
    }

    private fun slideUpBottomSheet() {
        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            binding.buttonEditProfile.setImageResource(R.drawable.ic_baseline_remove_24)

        } else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            binding.buttonEditProfile.setImageResource(R.drawable.ic_baseline_add_24)
            setProfile()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}