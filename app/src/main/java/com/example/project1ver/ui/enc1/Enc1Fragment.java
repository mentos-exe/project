package com.example.project1ver.ui.enc1;

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

public class Enc1Fragment extends Fragment {



    public static Enc1Fragment newInstance() {
        return new Enc1Fragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Enc1ViewModel enc1ViewModel =
                new ViewModelProvider(this).get(Enc1ViewModel.class);

        View root = inflater.inflate(R.layout.fragment_enc1, container, false);
        final TextView textView= root.findViewById(R.id.textView);

       final EditText editTextEnc1 = root.findViewById(R.id.editTextEnc1);
       final Button button = root.findViewById(R.id.button);
       enc1ViewModel.getText2().observe(getViewLifecycleOwner(), button::setText);
        final EditText number = root.findViewById(R.id.number);
        final EditText resultTextView = root.findViewById(R.id.resultTextView);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
               String s1 = String.valueOf(editTextEnc1.getText());
               int key = Integer.parseInt(number.getText().toString());
               boolean hasRussianLetters = checkForRussianLetters(s1);
              boolean hasnumber = checkFornumbers(key);


                  if (hasRussianLetters && hasnumber){
                      char [] newLine = new char[ s1.length()];
                      for (int i = 0; i < s1.length(); i++) {
                          char simvol = s1.charAt(i);
                          int index = ALPHABET.indexOf(simvol);
                          int newIn = index+key;
                          char neS= ALPHABET.charAt(newIn);
                          newLine[i] += neS  ;
                      }
                      resultTextView.setText(String.valueOf(newLine));
                  } else  {
                      showAlertDialog();
                  }




           }
       });

        final TextView resultTextView2 = root.findViewById(R.id.resultTextView2);
        final Button button2 = root.findViewById(R.id.button2);
        enc1ViewModel.getText3().observe(getViewLifecycleOwner(), button2::setText);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
                    String s1 = String.valueOf(resultTextView.getText());
                boolean hasRussianLetters = checkForRussianLetters(s1);
                int key = Integer.parseInt(number.getText().toString());
                boolean hasnumber = checkFornumbers(key);
                if (hasRussianLetters && hasnumber) {
                    char [] newLine = new char[ s1.length()];
                    for (int i = 0; i < s1.length(); i++) {
                        char simvol = s1.charAt(i);
                        int index = ALPHABET.indexOf(simvol);
                        int newIn = index-key;
                        char neS= ALPHABET.charAt(newIn);
                        newLine[i] += neS  ;
                    }
                    resultTextView2.setText(String.valueOf(newLine));

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
    private boolean checkFornumbers(int a){
        if (a>=1 && a<33){
            return true;
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
    private void showAlertDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Ошибка")
                .setMessage("Вы заполнили не все поля ввода")
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
