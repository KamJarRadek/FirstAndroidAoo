package com.example.docelowa1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.Spinner;
import android.widget.TextView;

public class ThirdScreen extends AppCompatActivity {
    private Spinner unitTypeSpiner;
    private EditText amountTextView;
    TextView wTextView, kwTextView,
            hpTextView, btuhTextView,
            btumTextView, butsTextView;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        setContentView(R.layout.layout_converter);
        Intent activityThatCalled = getIntent();

        addItemsToUnitTypesSpinner();
        addListnerToUnitTypesSpinner();
        amountTextView = (EditText) findViewById(R.id.amount_text_view);
        initializeTextView();



    }
    public void initializeTextView() {

        wTextView = (TextView) findViewById(R.id.w_text_view);
        kwTextView = (TextView) findViewById(R.id.kw_text_view);
        hpTextView = (TextView) findViewById(R.id.hp_text_view);
        btuhTextView = (TextView) findViewById(R.id.btuh_text_view);
        btumTextView = (TextView) findViewById(R.id.btum_text_view);
        butsTextView = (TextView) findViewById(R.id.btus_text_view);
    }

    public void addItemsToUnitTypesSpinner(){

        unitTypeSpiner = (Spinner) findViewById(R.id.unit_type_spinner);
        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter =
                ArrayAdapter.createFromResource(this, R.array.conversion_types,
                        android.R.layout.simple_spinner_item);

        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitTypeSpiner.setAdapter(unitTypeSpinnerAdapter);
    }

    public void addListnerToUnitTypesSpinner(){

        unitTypeSpiner = (Spinner) findViewById(R.id.unit_type_spinner);
        unitTypeSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {

                String itemSelectedInSpinner = parent.getItemAtPosition(pos).toString();
                checkIfConvertingFromKw(itemSelectedInSpinner);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void checkIfConvertingFromKw(String currentUnit){
        if(currentUnit.equals("kwat")){
            updateUnitTypeUsingKw(Quantity.Unit.kw);
        }else {
            if(currentUnit.equals("wat")){
                updateUnitTypeUsingOthers(Quantity.Unit.w);
            }
            if(currentUnit.equals("hp")){
                updateUnitTypeUsingOthers(Quantity.Unit.hp);
            }
            if(currentUnit.equals("btuh")){
                updateUnitTypeUsingOthers(Quantity.Unit.btuh);
            }
            if(currentUnit.equals("btum")){
                updateUnitTypeUsingOthers(Quantity.Unit.btum);
            }
            if(currentUnit.equals("btus")){
                updateUnitTypeUsingOthers(Quantity.Unit.buts);
            }
        }

    }
    public void updateUnitTypeUsingKw(Quantity.Unit currentUnit){
        double doubleToConvert =
                Double.parseDouble(amountTextView.getText().toString());

        String kwValueAndUnit = doubleToConvert + " kw";
        kwTextView.setText(kwValueAndUnit);

        updateUniteTextFieldUsingKw(doubleToConvert, Quantity.Unit.w, wTextView);
        updateUniteTextFieldUsingKw(doubleToConvert, Quantity.Unit.hp, hpTextView);
        updateUniteTextFieldUsingKw(doubleToConvert, Quantity.Unit.btuh, btuhTextView);
        updateUniteTextFieldUsingKw(doubleToConvert, Quantity.Unit.btum, btumTextView);
        updateUniteTextFieldUsingKw(doubleToConvert, Quantity.Unit.buts,  butsTextView);
    }

    public void updateUniteTextFieldUsingKw(double doubleToConvert,
                                            Quantity.Unit unitConvertingTo,
                                            TextView theTextView){
        Quantity unitQuantity = new Quantity(doubleToConvert, Quantity.Unit.kw);
        String tempUnit= unitQuantity.to(unitConvertingTo).toString();
        theTextView.setText(tempUnit);
    }

    public void updateUnitTypeUsingOthers(Quantity.Unit currentUnit){
        double doubleToConvert =
                Double.parseDouble(amountTextView.getText().toString());
        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);
        String valueInKw = currentQuantitySelected.to(Quantity.Unit.kw).toString();

        kwTextView.setText(valueInKw);

        updateUniteTextFieldUsingKw(doubleToConvert, currentUnit, Quantity.Unit.w, wTextView);
        updateUniteTextFieldUsingKw(doubleToConvert, currentUnit, Quantity.Unit.hp, hpTextView);
        updateUniteTextFieldUsingKw(doubleToConvert, currentUnit, Quantity.Unit.btuh, btuhTextView);
        updateUniteTextFieldUsingKw(doubleToConvert, currentUnit, Quantity.Unit.btum, btumTextView);
        updateUniteTextFieldUsingKw(doubleToConvert, currentUnit, Quantity.Unit.buts, butsTextView);

        if(currentUnit.name().equals(currentQuantitySelected.unit.name())){
            String currentUnitTextViewT = doubleToConvert + "     " +
                    currentQuantitySelected.unit.name();

            String textViewName = currentQuantitySelected.unit.name() + "_text_view";

            int currentId =
                    getResources().getIdentifier(textViewName,
                            "id", ThirdScreen.this.getPackageName());

            TextView currentTextView = (TextView) findViewById(currentId);
            currentTextView.setText(currentUnitTextViewT);

        }



    }

    public void updateUniteTextFieldUsingKw(double doubleToConvert,
                                            Quantity.Unit currentUnit,
                                            Quantity.Unit prefferedUnit,
                                            TextView targetTextView){

        Quantity currentQuantityToSelected = new Quantity(doubleToConvert, currentUnit);
        String temTextViewText =
                currentQuantityToSelected.to(Quantity.Unit.kw).to(prefferedUnit).toString();
        targetTextView.setText(temTextViewText);
    }
    public void onBackClick(View view){

        finish();
    }

}