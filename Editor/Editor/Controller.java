package Editor;

import java.lang.reflect.Field;
import java.util.Arrays;

import ComponentSystem.FlappyComponent;
import Core.FlappyEngine;
import Entity.GameObject;
import Entity.Scene;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Controller {

	@FXML
	Label sceneName;
	@FXML
	TreeView<GameObject> hierarchy;
	@FXML
	VBox listBox;
	@FXML
	CheckBox update;
	
	void updateHierarchy() {
		Scene scene = FlappyEngine.getCurrentScene();
		sceneName.setText(scene.title);
		
		TreeItem<GameObject> root = new TreeItem<GameObject>(scene);
		root.setExpanded(true);
		hierarchy.setRoot(root);
		
		for (GameObject child : scene.childs) {
			loopChilds(child, root);
		}
		
		hierarchy.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<GameObject>>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem<GameObject>> observable, TreeItem<GameObject> oldValue,
					TreeItem<GameObject> newValue) {
				if(newValue == null) return;
				
				update.setSelected(false);
				try {
					openObject(newValue.getValue());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
	public void loopChilds(GameObject p, TreeItem<GameObject> parent) {
		
		TreeItem<GameObject> childTI = new TreeItem<GameObject>(p);
		childTI.setExpanded(true);
		parent.getChildren().add(childTI);
		
		for (GameObject child : p.childs) {
			TreeItem<GameObject> c = new TreeItem<GameObject>(child);
			c.setExpanded(true);
			
			if(child.childs.size() > 0) {
				loopChilds(child, c);
			}
			parent.getChildren().add(c);
		}
	}
	public void openObject(GameObject object) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		
		Field[] fields = object.getClass().getFields();
		fields = Arrays.stream(fields).distinct().toArray(Field[]::new);
		
		listBox.getChildren().clear();
		Label titleLabel = new Label(object.name + " (" + object.getClass().getTypeName() + ")");
		titleLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		listBox.getChildren().add(titleLabel);
		listBox.getChildren().add(new Separator());
		
		for (Field field : fields) {
			
			Label fLabel = new Label("(" + field.getType().getSimpleName() + ") " + field.getName() + " : " + field.get(object));
			listBox.getChildren().add(fLabel);
		}
		
		
		Label componentLabel = new Label("Components");
		componentLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
		listBox.getChildren().add(new Separator());
		listBox.getChildren().add(componentLabel);

		for (FlappyComponent comp : object.components) {
			Label fLabel = new Label("Component: " + comp.getClass().getTypeName());
			listBox.getChildren().add(fLabel);
		}
	}
}
