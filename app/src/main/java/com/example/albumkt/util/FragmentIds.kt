package com.example.albumkt.util

interface FragmentIds {
    companion object {

        val FRAGMENT_ID: String
            get() = "fragment_id"
        /**
         * The base value
         */
        private val BASE: Int
            get() = 0x00
        /**
         * Video player Fragment
         */
        val VIDEO_PLAYER: Int
            get() = BASE + 1
        /**
         * Setting Fragment
         */
        val SETTING: Int
            get() = BASE + 2
        /**
         * Feedback Fragment
         */
        val FEEDBACK: Int
            get() = BASE + 3

        val BARCODE_FEN: Int
            get() = BASE + 4
        val ABOUT: Int
            get() = BASE + 5
    }
}