package com.example.html_editor;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class HelloController {
    public WebView webview;
    public HTMLEditor htmleditor;
    public TextArea textarea;
    public TextField urlField;

    public void onOpen(ActionEvent actionEvent)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Document");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(textarea.getScene().getWindow());
        if (file != null) {
            load(file);
        }
    }

    //загрузка из выбранного файла
    private void load (File file){
        try (Scanner input = new Scanner(file, StandardCharsets.UTF_8)) {
            StringBuilder htmlContent = new StringBuilder();
            while (input.hasNextLine()) {
                htmlContent.append(input.nextLine()).append("\n");
            }
            String html = htmlContent.toString();
            htmleditor.setHtmlText(html);
            webview.getEngine().loadContent(html);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onSave(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Document");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(textarea.getScene().getWindow());
        if (file != null) {
            String content = htmleditor.getHtmlText();
            saveToFile(content, file);
        }
    }

    private void saveToFile(String content, File file) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadURL(ActionEvent actionEvent) {
        String url = urlField.getText();
        if (isValidUrl(url)) {
            try {
                URL pageUrl = new URL(url);
                BufferedReader reader = new BufferedReader(new InputStreamReader(pageUrl.openStream()));
                StringBuilder htmlContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    htmlContent.append(line).append("\n");
                }
                reader.close();
                String html = htmlContent.toString();
                htmleditor.setHtmlText(html);
                webview.getEngine().loadContent(html);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isValidUrl(String url) {
        // Add your URL validation logic here
        return url != null && !url.isEmpty();
    }

    //внесение изменений в редакторе
    public void onChange(ActionEvent actionEvent) {
        textarea.setText(htmleditor.getHtmlText());
        webview.getEngine().loadContent(htmleditor.getHtmlText());
    }

}