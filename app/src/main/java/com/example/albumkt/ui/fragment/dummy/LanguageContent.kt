package com.example.albumkt.ui.fragment.dummy

import java.util.*

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object LanguageContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<LanguageItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, LanguageItem> = HashMap()

    private val COUNT = 3

    init {
        // Add some sample items.
        for (i in 0 until COUNT) {
            addItem(createLanguageItem(i))
        }
    }

    private fun addItem(item: LanguageItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createLanguageItem(position: Int): LanguageItem {

        val content = when (position) {
            0 -> {
                "简体中文"
            }
            1 -> {
                "繁体中文"
            }
            2 -> {
                "English"
            }
            else -> {
                ""
            }
        }

        return LanguageItem(position.toString(), content, makeDetails(position))
    }

    private fun makeDetails(position: Int): String {
        return when (position) {
            0 -> {
                "zh-rCN"
            }
            1 -> {
                "zh-rTW"
            }
            2 -> {
                "en-rUS"
            }
            else -> {
                ""
            }
        }
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class LanguageItem(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}
