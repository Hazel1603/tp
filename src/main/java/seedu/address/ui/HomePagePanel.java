package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.booking.Booking;

public class HomePagePanel extends UiPart<Region> {
    private static final String FXML = "HomePagePanel.fxml";

    @FXML
    private Label roomsAvailableTitle;

    @FXML
    private Label singleRoomsText;

    @FXML
    private Label doubleRoomsText;

    @FXML
    private Label suiteRoomsText;

    @FXML
    private Label recentBookingsTitle;

    @FXML
    private ListView<Booking> recentBookingsView;


    /**
     * Creates a HomePagePanel
     */
    public HomePagePanel(ObservableList<Booking> bookingList) {
        super(FXML);
        roomsAvailableTitle.setText("Number of Rooms Available:");
        singleRoomsText.setText(
                "10 Single Rooms");
        doubleRoomsText.setText(
                "10 Double Rooms");
        suiteRoomsText.setText(
                "10 Suite Rooms");
        recentBookingsTitle.setText("Recently Checked In:");
        SortedList<Booking> sortedBookingList = new SortedList<>(bookingList, (o1, o2)
            -> o2.getStartDate().compareTo(o1.getStartDate()));
        recentBookingsView.setItems(sortedBookingList);
        recentBookingsView.setCellFactory(listView -> new RecentBookingCell());
    }

    class RecentBookingCell extends ListCell<Booking> {
        @Override
        protected void updateItem(Booking booking, boolean empty) {
            super.updateItem(booking, empty);
            if (empty || booking == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new Label(
                        String.format("%s. Person %s, Room Number %s, %s",
                                getIndex() + 1, booking.getPersonId(),
                                booking.getRoomId(), booking.getStartDate().toString())
                ));
            }
        }
    }
}