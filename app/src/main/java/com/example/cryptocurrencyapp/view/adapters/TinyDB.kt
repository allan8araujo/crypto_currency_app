package com.example.cryptocurrencyapp.view.adapters

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import java.io.*


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

class TinyDB(c: Context) :
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

    private fun categoria(context: Context): File {
        return File(context.filesDir, FAVORITOS)
    }

    private fun populaHash() {
        arquivos["favoritos"] = FAVORITOS
        arquivos["animal"] = ANIMAL
        arquivos["pais"] = PAIS
        arquivos["disciplina"] = DISCIPLINA
    }

    fun populador(context: Context) {
        val arrStr = HashMap<String, String>()
        arrStr["favoritos"]
        arrStr["animal"] = """
               abelha
               andorinha
               macaco
               baleia
               cachorro
               hipopotamo
               ema
               elefante
               texugo
               gato
               
               """.trimIndent()
        arrStr["pais"] = """
               egito
               alemanha
               argentina
               australia
               holanda
               brasil
               china
               canada
               inglaterra
               chile
               
               """.trimIndent()
        arrStr["disciplina"] = """
               matematica
               sociologia
               ingles
               quimica
               espanhol
               biologia
               geografia
               filosofia
               ciencias
               historia
               
               """.trimIndent()
        for (nomeDoBanco in arrStr.keys) {
            try {
                val bw = BufferedWriter(FileWriter(categoria(context), true))
                bw.write(arrStr[nomeDoBanco])
                bw.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun ler(): ArrayList<String> {
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

    fun escrever(palavra: String?) {
        try {
            val bw = BufferedWriter(FileWriter(nomeDaCategoria, true))
            bw.write(palavra)
            bw.newLine()
            bw.close()
        } catch (e: IOException) {
            e.printStackTrace()
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
        nomeDaCategoria = categoria(c)
    }
}