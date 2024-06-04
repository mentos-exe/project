package com.example.project1ver.ui.enc2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project1ver.R;

public class Enc2Fragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Enc2ViewModel enc2ViewModel =
                new ViewModelProvider(this).get(Enc2ViewModel.class);

        View root = inflater.inflate(R.layout.fragment_enc2, container, false);

        final EditText text = root.findViewById(R.id.text);
        final Button encoder = root.findViewById(R.id.encoder);
        enc2ViewModel.getText2().observe(getViewLifecycleOwner(), encoder::setText);
        final EditText resultTextView = root.findViewById(R.id.resultTextView);

        encoder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder encryptedText = new StringBuilder();
                String request = String.valueOf(text.getText());
                String russianAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
                String reversedAlphabet = "яюэьыъщшчцхфутсрпонмлкйизжёедгвба";
                boolean hasRussianLetters = checkForRussianLetters(request);
                if (hasRussianLetters){
                for (int i = 0; i < text.length(); i++) {
                    char currentChar = request.charAt(i);
                    if (Character.isLetter(currentChar)) {
                        int index = russianAlphabet.indexOf(Character.toLowerCase(currentChar));
                        char encryptedChar = reversedAlphabet.charAt(index);
                        encryptedText.append(Character.isUpperCase(currentChar) ? Character.toUpperCase(encryptedChar) : encryptedChar);
                    } else {
                        encryptedText.append(currentChar);
                    }
                }
                resultTextView.setText(encryptedText);
                } else {
                    showAlertDialog();
                }
            }


        });

        final TextView resultTextView2 = root.findViewById(R.id.resultTextView2);
        final Button decoder = root.findViewById(R.id.decoder);
        enc2ViewModel.getText3().observe(getViewLifecycleOwner(), decoder::setText);

        decoder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String request = String.valueOf(resultTextView.getText());
                boolean hasRussianLetters = checkForRussianLetters(request);


                    if (hasRussianLetters) {
                        String russianAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
                        String reversedAlphabet = "яюэьыъщшчцхфутсрпонмлкйизжёедгвба";

                        StringBuilder decryptedText = new StringBuilder();

                        for (int i = 0; i < request.length(); i++) {
                            char currentChar = request.charAt(i);
                            if (Character.isLetter(currentChar)) {
                                int index = reversedAlphabet.indexOf(Character.toLowerCase(currentChar));
                                char decryptedChar = russianAlphabet.charAt(index);
                                decryptedText.append(Character.isUpperCase(currentChar) ? Character.toUpperCase(decryptedChar) : decryptedChar);
                            } else {
                                decryptedText.append(currentChar);
                            }
                        }
                        resultTextView2.setText(decryptedText);

                    } else {
                        showAlertDialog();
                    }

            }
        });
        return root;
    }
    private boolean checkForRussianLetters(String text) {
        for (char c : text.toCharArray()) {
            if ((c >= 'а' && c <= 'я') || (c >= 'А' && c <= 'Я') || c == 'ё' || c == 'Ё') {
                return true;
            }
        }
        return false;
    }
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Ошибка")
                .setMessage("Вы используете некорректные символы")
                // Дополнительные действия, например, добавление кнопок
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Действие при нажатии на кнопку "OK"
                        dialog.dismiss();

                    }
                })

                .show();
    }

}