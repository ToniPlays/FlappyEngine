package Editor;

import java.lang.reflect.Field;
import java.util.Arrays;

import Entity.Object;
import MainCore.FlappyComponent;
import MainCore.FlappyEngine;
import Renderers.Scene;
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
	TreeView<Object> hierarchy;
	@FXML
	VBox listBox;
	@FXML
	CheckBox update;
	
	void updateHierarchy() {
		Scene scene = FlappyEngine.getCurrentScene();
		sceneName.setText(scene.title);
		
		TreeItem<Object> root = new TreeItem<Object>(scene);
		root.setExpanded(true);
		hierarchy.setRoot(root);
		
		for (Object child : scene.childs) {
			loopChilds(child, root);
		}
		
		hierarchy.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Object>>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem<Object>> observable, TreeItem<Object> oldValue,
					TreeItem<Object> newValue) {
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
	public void loopChilds(Object p, TreeItem<Object> parent) {
		
		TreeItem<Object> childTI = new TreeItem<Object>(p);
		childTI.setExpanded(true);
		parent.getChildren().add(childTI);
		for (Object child : p.childs) {
			TreeItem<Object> c = new TreeItem<Object>(child);
			c.setExpanded(true);
			
			if(child.childs.size() > 0) {
				loopChilds(child, c);
			}
			parent.getChildren().add(c);
		}
	}
	public void openObject(Object object) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		
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
