package com.example.animehub.data

import com.example.animehub.R
import com.example.animehub.model.AnimeElement

object Datasource {

    private val baseAnimeList = listOf(
        AnimeElement("Attack on Titan", 1, 9.04, "aot", "La humanidad lucha por sobrevivir contra los Titanes.", isFavorite = true),
        AnimeElement("Death Note", 2, 8.62, "death_note", "Un estudiante encuentra un cuaderno que puede matar a quien escriba su nombre."),
        AnimeElement("Fullmetal Alchemist: Brotherhood", 3, 9.10, "fma", "Dos hermanos buscan la piedra filosofal para recuperar sus cuerpos."),
        AnimeElement("Jujutsu Kaisen", 4, 8.70, "jujutsu", "Un joven se une a una organización secreta de Hechiceros Jujutsu."),
        AnimeElement("One Piece", 5, 8.71, "one_piece", "Las aventuras de Monkey D. Luffy y su tripulación.", isFavorite = true),
        AnimeElement("Hunter x Hunter", 6, 9.05, "hxh", "Gon Freecss se propone convertirse en Cazador."),
        AnimeElement("My Hero Academia", 7, 8.00, "mha", "Un mundo donde el 80% de la población nace con superpoderes."),
        AnimeElement("Spy x Family", 8, 8.52, "spyxfamily", "Un espía, una asesina y una telépata forman una familia falsa."),
        AnimeElement("Demon Slayer", 9, 8.79, "demon_slayer", "Un chico busca la cura para su hermana, convertida en demonio."),
        AnimeElement("Chainsaw Man", 10, 8.42, "chainsawman", "Un joven se convierte en híbrido humano-demonio."),
        AnimeElement("Haikyuu", 11, 8.92, "haikyuu", "Shoyo Hinata busca convertirse en el mejor jugador de voleibol."),
        AnimeElement("Gintama", 12, 9.00, "gintama", "Una comedia ambientada en el Japón feudal invadido por alienígenas."),
        AnimeElement("Steins;Gate", 13, 8.85, "steinsgate", "Estudiantes descubren una forma de enviar mensajes al pasado."),
        AnimeElement("Vinland Saga", 14, 8.75, "vinlandsaga", "La historia épica del vikingo Thorfinn."),
        AnimeElement("Cowboy Bebop", 15, 8.76, "cowboybebop", "Cazadores de recompensas que viajan por el sistema solar."),
    )

    val getAnimeElements: () -> MutableList<AnimeElement> = {
        val list = baseAnimeList.toMutableList()
        list.shuffle()
        list
    }

    val getElementByName: (String) -> AnimeElement? = { name ->
        baseAnimeList.find { it.name == name }
    }

    fun getDrawableIdByName(name: String): Int {
        return when (name) {
            "aot" -> R.drawable.aot
            "death_note" -> R.drawable.death_note
            "fma" -> R.drawable.fma
            "jujutsu" -> R.drawable.jujutsu
            "one_piece" -> R.drawable.one_piece
            "hxh" -> R.drawable.hxh
            "mha" -> R.drawable.mha
            "spyxfamily" -> R.drawable.spyxfamily
            "demon_slayer" -> R.drawable.demon_slayer
            "chainsawman" -> R.drawable.chainsawman
            "haikyuu" -> R.drawable.haikyuu
            "gintama" -> R.drawable.gintama
            "steinsgate" -> R.drawable.steinsgate
            "vinlandsaga" -> R.drawable.vinlandsaga
            "cowboybebop" -> R.drawable.cowboybebop
            else -> R.drawable.animehub
        }
    }
}