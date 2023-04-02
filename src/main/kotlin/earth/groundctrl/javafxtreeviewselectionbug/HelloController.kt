package earth.groundctrl.javafxtreeviewselectionbug

import javafx.beans.binding.Bindings
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.SelectionMode
import javafx.scene.control.TreeView

class HelloController {
    @FXML
    private lateinit var tree: TreeView<String>

    @FXML
    private lateinit var customLabel: Label

    @FXML
    private lateinit var itemsLabel: Label

    @FXML
    private lateinit var indicesLabel: Label

    fun initialize() {
        tree.selectionModel.selectionMode = SelectionMode.MULTIPLE
        itemsLabel.textProperty().bind(
            Bindings.createStringBinding(
                { tree.selectionModel.selectedItems.map { it?.value }.toString() },
                tree.selectionModel.selectedItemProperty(), tree.selectionModel.selectedItems, tree.focusModel.focusedItemProperty()
            )
        )
        indicesLabel.textProperty().bind(
            Bindings.createStringBinding(
                { tree.selectionModel.selectedIndices.toString() },
                tree.selectionModel.selectedIndexProperty(), tree.selectionModel.selectedIndices, tree.focusModel.focusedItemProperty()
            )
        )
    }

    @FXML
    private fun onButtonClick() {
        tree.selectionModel.selectedItem.parent?.children?.remove(tree.selectionModel.selectedItem)
    }
}
