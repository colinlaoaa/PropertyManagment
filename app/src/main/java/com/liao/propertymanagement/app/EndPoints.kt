package com.liao.propertymanagement.app


class Endpoints {

    companion object {


        fun getAPIData(language: String, spoken_language_code: String): String {
            return Config.BASE_URL + "repositories?language=$language&since=daily&spoken_language_code=$spoken_language_code"
        }


    }

}