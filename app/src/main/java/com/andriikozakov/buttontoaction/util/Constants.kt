package com.andriikozakov.buttontoaction.util

class Constants {
    class Network {
        companion object {
            const val URL_BASE_SERVER = "https://s3-us-west-2.amazonaws.com/"
            const val URL_CONFIGURATION = "androidexam/butto_to_action_config.json"
        }

        class Params {
            companion object {
                const val DEFAULT = "buttontoaction.DEFAULT"
                const val SUCCESS = "buttontoaction.SUCCESS"
                const val ERROR = "buttontoaction.ERROR"
                const val ACTION = "buttontoaction.action"
            }
        }
    }

    class Fragments {
        companion object
    }

    class RequestCode {
        companion object {
            const val CHOOSE_CONTACT = 42
        }
    }

    companion object {
        const val PREFERENCE_MAIN = "buttontoaction.sharedpreferences"
        const val ACTION_CHOOSE_CONTACT = "choose_contact"
        const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        const val TIME_ZONE = "UTC"
    }
}