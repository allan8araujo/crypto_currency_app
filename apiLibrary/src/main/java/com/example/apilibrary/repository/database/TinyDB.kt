package com.example.apilibrary.repository.database

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.apilibrary.repository.const.Constants.Companion.DATABASE_DIR
import java.io.*

class TinyDB(val context: Context) :
    AppCompatActivity(), Serializable {
    var categoryFile: File
    private val fileMap = HashMap<String, String>()

    private fun verifyFile(c: Context): Boolean {
        var flag = false
        var file: File
        for (fileItem in fileMap.values) {
            file = c.getFileStreamPath(fileItem)
            flag = if (file.exists()) {
                true
            } else {
                return false
            }
        }
        return flag
    }

    private fun getCategory(): File {
        return File(context.filesDir, FAVORITOS)
    }

    private fun populateHash() {
        fileMap["favoritos"] = FAVORITOS
    }

    fun getAll(): ArrayList<String> {
        val assetItemList = ArrayList<String>()
        try {
            val br = BufferedReader(FileReader(categoryFile))
            while (br.ready()) {
                assetItemList.add(br.readLine())
            }
            br.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return assetItemList
    }

    fun addItem(word: String?) {
        try {
            val bw = BufferedWriter(FileWriter(categoryFile, true))
            bw.write(word)
            bw.newLine()
            bw.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun hasItem(id: String): Boolean {
        return getAll().contains(id)
    }

    fun getItem(id: String): String? {
        return getAll().find { s -> s.equals(id) }
    }

    fun removeItem(word: String) {
        val list: ArrayList<String> = getAll()
        try {
            File(context.filesDir, FAVORITOS).delete()
            categoryFile = getCategory()
        } catch (e: Exception) {
            TODO()
        }
        list.forEach { id ->
            if (id != word)
                addItem(id)
        }
    }

    companion object {
        const val FAVORITOS = DATABASE_DIR
    }

    init {
        populateHash()
        categoryFile = getCategory()
    }
}
