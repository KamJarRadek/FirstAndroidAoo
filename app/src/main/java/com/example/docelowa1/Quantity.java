package com.example.docelowa1;

import android.support.annotation.Dimension;

import java.text.DecimalFormat;

public class Quantity {

    final double value;
    final Unit unit;

    public static enum Unit{
        w(1000), kw(1.0),
        hp(1.3410), btuh(3412.1),
        btum(56.869), buts(0.9478);

        final static Unit baseUnit = kw;
        final double byBaseUnit;

        private Unit(double inKw){
            this.byBaseUnit=inKw;
        }

        public double toBaseUnit(double value){
            return value/byBaseUnit;
        }
        public double fromBaseUnit(double value){

            return  value*byBaseUnit;
        }


    }
    public Quantity(double value, Unit unit){

        super();
        this.value=value;
        this.unit=unit;
    }

    public Quantity to(Unit newunit){
        Unit oldUnit = this.unit;
        return new Quantity(newunit.fromBaseUnit(oldUnit.toBaseUnit(value)), newunit);


    }
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.0000");
        return df.format(value) + unit.name();
    }
}
