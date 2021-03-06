package Editor;
	

import Core.FlappyEngine;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Editor extends Application implements Runnable {
	
	Controller controller;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setOnCloseRequest(event -> FlappyEngine.log("Application closed by Editor", FlappyEngine.ERROR));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Editor.fxml"));
			Scene scene = new Scene(loader.load(), 800, 600);
			controller = (Controller) loader.getController();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle(FlappyEngine.VERSION + " editor");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			Thread thread = new Thread(new Runnable() {
				
				 @Override
		            public void run() {
		                Runnable updater = new Runnable() {

		                    @Override
		                    public void run() {
		                        controller.updateHierarchy();
		                        if(controller.selected != null) {
		                        	try {
										controller.openObject(controller.selected);
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
		                    }
		                };

		                while (true) {
		                	
		                	if(controller.update.isSelected()) {
			                	
			                    try {
			                        Thread.sleep(100);
			                    } catch (InterruptedException ex) {
			                    	
			                    }
			                    Platform.runLater(updater);
		                	}
		                }
		            }
			});
			thread.setDaemon(true);
			thread.start();
			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		launch(new String[0]);
		
	}
}
