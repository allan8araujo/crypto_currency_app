package com.example.cryptocurrencyapp.view.adapters

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*
import java.lang.Exception


//
//class tinyDB(val view: Context) {
//
//    val sharedPref: SharedPreferences? = view.getSharedPreferences(
//        "ASSETS", 0
//    )
//    var assetsList: String? = sharedPref?.getString("ASSET_ID", "null")
//    val editor = sharedPref?.edit()
//
//    fun addFavorites(id: String) {
//
//        assetsList = "; " + id
//        editor?.putString("ASSETS_ID", assetsList)
//        editor?.apply()
//        Toast.makeText(view, "Moeda adicionada com sucesso", Toast.LENGTH_LONG).show()
//
//    }
//
//    fun removeFavorites(id: String) {
//
//        assetsList?.replace(id, "")
//        editor?.putString("ASSETS_ID", assetsList)
//        editor?.apply()
//        Toast.makeText(view, "Moeda removido com sucesso", Toast.LENGTH_LONG).show()
//
//    }
//
//    fun getFavorites(): List<String>? {
//        val x =view?.getSharedPreferences("ASSETS", 0)
//            ?.getString("ASSET_ID", "NULL")
//            ?.split(";")
//        return x
//    }
//
//    fun hasAssetId(id: String): Boolean {
//        val list = getFavorites()
//        val item = list?.contains(id)
//        return  item == true
//    }
//}

class TinyDB(val context: Context) :
    AppCompatActivity(), Serializable {
    var nomeDaCategoria: File
    private val arquivos = HashMap<String, String>()
    private fun verificaArquivos(c: Context): Boolean {
        var flag = false
        var file: File
        for (arquivo in arquivos.values) {
            file = c.getFileStreamPath(arquivo)
            flag = if (file.exists()) {
                true
            } else {
                return false
            }
        }
        return flag
    }

    private fun categoria(): File {
        return File(context.filesDir, FAVORITOS)
    }

    private fun populaHash() {
        arquivos["favoritos"] = FAVORITOS
        arquivos["animal"] = ANIMAL
        arquivos["pais"] = PAIS
        arquivos["disciplina"] = DISCIPLINA
    }

    fun getAll(): ArrayList<String> {
        val leitura = ArrayList<String>()
        try {
            val br = BufferedReader(FileReader(nomeDaCategoria))
            while (br.ready()) {
                leitura.add(br.readLine())
            }
            br.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return leitura
    }

    fun addItem(palavra: String?) {
        try {
            val bw = BufferedWriter(FileWriter(nomeDaCategoria, true))
            bw.write(palavra)
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

    fun removeItem(palavra: String) {
        val list: ArrayList<String> = getAll()
        try {
            File(context.filesDir, FAVORITOS).delete()
            nomeDaCategoria = categoria()
        } catch (e: Exception) {
            TODO()
        }
        list.forEach { id ->
            if (id != palavra)
                addItem(id)
        }

    }

    companion object {
        const val FAVORITOS = "favoritos.txt"
        const val ANIMAL = "animal.txt"
        const val PAIS = "pais.txt"
        const val DISCIPLINA = "disciplina.txt"
    }

    init {
        populaHash()
        nomeDaCategoria = categoria()
    }
}