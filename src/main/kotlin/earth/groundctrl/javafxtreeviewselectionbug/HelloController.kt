package earth.groundctrl.javafxtreeviewselectionbug

import javafx.beans.binding.Bindings
import javafx.collections.ListChangeListener
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

        tree.selectionModel.selectedItems.addListener(ListChangeListener {
            while (it.next()) {
                if (tree.selectionModel.selectedItem in it.removed) {
                    if (it.from == 0) {
                        tree.selectionModel.select(it.list.firstOrNull())
                    } else {
                        tree.selectionModel.select(it.list.lastOrNull())
                    }
                }
            }
        })

        tree.focusModel.focusedItemProperty().addListener { _, _, new ->
            if (tree.selectionModel.selectedItem?.parent == null) {
                tree.selectionModel.select(new)
            }
        }
    }

    @FXML
    private fun onButtonClick() {
        tree.selectionModel.selectedItem.parent?.children?.remove(tree.selectionModel.selectedItem)
    }
}
