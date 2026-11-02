package edu.cnm.deepdive.skywatch.javafx.adapter

import edu.cnm.deepdive.skywatch.core.model.SearchResultImage
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.util.Callback

class SearchResultImageAdapter :
    Callback<ListView<SearchResultImage>, ListCell<SearchResultImage>> {

    override fun call(param: ListView<SearchResultImage>): ListCell<SearchResultImage> {
        TODO("Not yet implemented")
    }

    private class ResultCell : ListCell<SearchResultImage> {

        private val root: Parent

        constructor() {
            val loader = FXMLLoader(javaClass.classLoader.getResource("search-result.fxml"))
            loader.setController(this)
            root = loader.load()
        }

    }

}