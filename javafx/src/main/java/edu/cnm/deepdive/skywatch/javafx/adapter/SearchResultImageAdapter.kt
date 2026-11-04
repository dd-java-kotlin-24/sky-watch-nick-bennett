package edu.cnm.deepdive.skywatch.javafx.adapter

import edu.cnm.deepdive.skywatch.core.model.SearchResultImage
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.util.Callback

class SearchResultImageAdapter :
    Callback<ListView<SearchResultImage>, ListCell<SearchResultImage>> {

    override fun call(param: ListView<SearchResultImage>): ListCell<SearchResultImage> =
        ResultCell()

    private class ResultCell : ListCell<SearchResultImage> {

        private val root: Parent

        @FXML
        private lateinit var description: Label
        @FXML
        private lateinit var thumbnail: ImageView

        constructor() : super() {
            val loader = FXMLLoader(javaClass.classLoader.getResource("search-result.fxml"))
            loader.setController(this)
            root = loader.load()
            text = null // setText(null)
            graphic = null // setGraphic(null)
        }

        override fun updateItem(item: SearchResultImage?, empty: Boolean) {
            super.updateItem(item, empty)
            if (empty || item == null) {
                graphic = null
            } else {
                description.text = item.description
                thumbnail.image = Image(item.thumbnail.toString())
                graphic = root
            }
        }
    }

}