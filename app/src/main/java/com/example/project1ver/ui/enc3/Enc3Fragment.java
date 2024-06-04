package com.example.project1ver.ui.enc3;

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

public class Enc3Fragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Enc3ViewModel enc3ViewModel =
                new ViewModelProvider(this).get(Enc3ViewModel.class);

        View root = inflater.inflate(R.layout.fragment_enc3, container, false);

        final EditText text = root.findViewById(R.id.text);
        EditText key1 = root.findViewById(R.id.key);
        final Button encoder = root.findViewById(R.id.encoder);
        enc3ViewModel.getText2().observe(getViewLifecycleOwner(), encoder::setText);
        final EditText resultTextView = root.findViewById(R.id.resultTextView);

        encoder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String request = String.valueOf(text.getText());
                String key = String.valueOf(key1.getText());

                boolean hasRussian = checkForRussianLetters(request);
                if(hasRussian) {
                    if (request.length() != key.length()) {
                        showAlertDialog0();
                    } else {

                        StringBuilder chipherText = new StringBuilder();
                        for (int i = 0; i < request.length(); i++) {
                            char encriptedChar = (char) (request.charAt(i) ^ key.charAt(i));
                            chipherText.append(encriptedChar);
                        }

                        resultTextView.setText(chipherText);
                    }
                }else{
                    showAlertDialog();
                }
            }
        });


        final TextView resultTextView2 = root.findViewById(R.id.resultTextView2);
        final Button decoder = root.findViewById(R.id.decoder);
        enc3ViewModel.getText3().observe(getViewLifecycleOwner(), decoder::setText);

        decoder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String request1 = String.valueOf(resultTextView.getText());
                String key = String.valueOf(key1.getText());
                StringBuilder result = new StringBuilder();


                    for (int i = 0; i < request1.length(); i++) {
                        char encryptedChar = request1.charAt(i);
                        char keyChar = key.charAt(i%key.length());
                        char decryptedChar = (char) (encryptedChar ^ keyChar);
                        result.append(decryptedChar);
                    }


                    resultTextView2.setText(result);



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
    private void showAlertDialog0() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Ошибка")
                .setMessage("Длина шифруемой строки не совпадает с длиной ключа.")
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