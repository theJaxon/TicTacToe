package com.tictactoe.tictactoefx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import actions.App;
import actions.PlayRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import player.PlayerHandler;


public class SwitchTo {

    static ArrayList<String> ScenesList = new ArrayList<String>() {
        {
            add("Scene"); // 0 ==> Scene.fxml
            add("RegisterForm"); // 1 ==> RegisterForm.fxml
            add("Dashboard"); // 2 ==> Dashboard.fxml
            add("GamePlay"); // 3 ==> GamePlay.fxml
            add("DifficultySelection");//4 ==> DifficultySelection.fxml
            add("ChangeProfilePicture"); //5 ==> ChangeProfilePicture.fxml
        }
    };

    static ArrayList<String> popUpList = new ArrayList<String>() {
        {
            add("OnlineListPopup"); // 0 ==> OnlineListPopup.fxml
            add("InvitationRequestPopup");// 1==> InvitationRequestPopup.fxml
            add("RequestRejectedPopup"); //2 ==> RequestRejectedPopup.fxml
            add("SaveGameRejectedPopup"); //3 ==> SaveGameRejectedPopup.fxml
            add("SaveGameRequestPopup"); //4 ==> SaveGameRequest.fxml
            add("SaveGameSuccessPopup"); //5 ==> SaveGameSuccessPopup.fxml
            add("WinnerPopup"); //6 ==> Winner.fxml
            add("GoogleLoginPopup"); // 7 ==> GoogleLoginPopup.fxml
            add("WrongCredentialsPopup"); // 8 ==> WrongCredentialsPopup.fxml
            add("RegisterationSuccessfulPopup"); // 9 ==> RegisterationSuccessfulPopup.fxml
            add("SaveGameConfirmationPopup"); //10 ==> SaveGameConfirmationPopup.fxml
            add("ServerErrorPopup"); // 11 ==> ServerErrorPopup.fxml
            add("GameFailedPopup"); //12 ==> GameFailedPopup.fxml
            add("ResetPopup"); // 13 ==> ResetPopup.fxml
        }
    };

    public static void transition(ActionEvent event, int sceneID) throws IOException {
        //Transition to the RegisterForm.fxml scene
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        changeTo(window, sceneID);

    }

    public static void changeTo(Stage window, int sceneID) throws IOException {
        Parent root = FXMLLoader.load(SwitchTo.class.getResource("/fxml/" + ScenesList.get(sceneID) + ".fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.setResizable(false);
        window.show();
        if(sceneID==2)
            PlayerHandler.updatePlayers();
    }

    public static void popupTransition(int popUpID, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SwitchTo.class.getResource("/popupsfxml/" + popUpList.get(popUpID) + ".fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        App.setPopUpWindow(stage);
//        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public static void mainScene(ActionEvent event) throws IOException {
        transition(event, 0);
    }

    public static void registerFormScene(ActionEvent event) throws IOException {
        transition(event, 1);
    }


    public static void dashboardScene(ActionEvent event) throws IOException {
        transition(event, 2);
    }

    public static void gamePlayScene(ActionEvent event) throws IOException {
        transition(event, 3);
    }

    public static void DifficultySelectionScene(ActionEvent event) throws IOException {
        transition(event, 4);
    }

    public static void ChangeProfilePictureScene(ActionEvent event) throws  IOException
    {
        transition(event, 5);
    }

    public static void onlineListPopUpScene() throws IOException {
        popupTransition(0, "Online List");
    }

    public static void InvitationRequestPopupScene() throws IOException {

        popupTransition(1, "Invitation To Play");
    }


    public static void RequestRejectedPopupScene() throws IOException {
        popupTransition(2, "Oops,Game Rejected");
    }

    public static void SaveGameRejectedPopupScene() throws IOException {
        popupTransition(3, "Game save  request was rejected");
    }

    public static void SaveGameRequestPopupScene() throws IOException {
        popupTransition(4, "Request for game save");
    }

    public static void SaveGameSuccessPopupScene() throws IOException {
        popupTransition(5, "Game save success");
    }

    public static void WinnerPopupScene() throws IOException {
        popupTransition(6, "Game Result");
    }

    public static void GooglePopupScene() throws IOException
    {
        popupTransition(7, "Login with Google");
    }

    public static void WrongCredentialsPopupScene() throws IOException
    {
        popupTransition(8, "Wrong Credentials were entered");
    }

    public static void RegisterationSuccessfullPopupScene() throws IOException
    {
        popupTransition(9, "Successfully Registered");
    }

    public static void SaveGameConfirmationPopupScene() throws IOException
    {
        popupTransition(10, "An already saved game was found");
    }

    public static void ServerErrorPopupScene() throws IOException
    {
        popupTransition(11, "Server error!");
    }

    public static void GameFailedPopupScene() throws  IOException
    {
        popupTransition(12, "Game failed");
    }
    
    public static void ResetPopupScene() throws IOException
    {
        popupTransition(13, "Game reset request");
    }
}


