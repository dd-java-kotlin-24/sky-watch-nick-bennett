package edu.cnm.deepdive.skywatch.javafx.adapter

import edu.cnm.deepdive.skywatch.core.model.SearchResultImage
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.util.Callback

class SearchResultImageAdapter :
    Callback<ListView<SearchResultImage>, ListCell<SearchResultImage>> {

    override fun call(param: ListView<SearchResultImage>): ListCell<SearchResultImage> =
        ResultCell()

    private class ResultCell : ListCell<SearchResultImage> {

        private val root: Parent

        // TODO: Declare properties matching name & type of widgets in search-result.fxml. Each must
        //       have the @FXML annotation, and each must include the lateinit modifier.

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
                // TODO: Copy values from item to the contents of our widgets.
                graphic = root
            }
        }
    }

}