import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class tableTest extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	TableView<Double[]> table;
	private final ObservableList<Double[]> data = FXCollections
			.observableArrayList();

	public void start(Stage arg0) throws Exception {
		Stage stage = new Stage();
		stage.setTitle("test Table");

		table = new TableView<Double[]>();

		for (int i = 0; i < 3; i++) {
			TableColumn<Double[], Double> col = new TableColumn<Double[], Double>(
					"col " + i);
			col.setPrefWidth(50);
			table.getColumns().add(col);
		}

		for (int i = 0; i < 3; i++) {
			Double[] temp = new Double[3];
			Arrays.fill(temp, new Double(0));
			data.add(temp);
		}
		table.setItems(data);

		Group root = new Group(table);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
