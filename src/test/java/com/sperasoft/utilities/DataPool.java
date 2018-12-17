package com.sperasoft.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.sperasoft.models.Account;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class DataPool<T> {

    Collection<T> tCollection;

    public void processDataFile( String filePath, Class<T> tClass){

        tCollection = new ArrayList<T>();

        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        objectMapper.setDateFormat( dateFormat );
//        System.out.println(tClass.getName());
        try {
//            T t = objectMapper.readValue( new File( filePath ), tClass );
            ArrayList<T> t = objectMapper.readValue( new File( filePath ),
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass) );
            tCollection.addAll( t );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object[][] getData() {

        Object[][] data = new Object[ tCollection.size() ][ 1 ];

        Iterator<T> it = tCollection.iterator();

        int i = 0;
        while( it.hasNext() ) {
            data[ i ][ 0 ] = it.next();
            i++;
        }

        return data;
    }
}