package com.company.books.app.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {

    private final ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

    public ArrayList<HashMap<String, String>> getMetadata() {
        return this.metadata;
    }

    public void setMetadata(String tipo, String codigo, String date) {
        HashMap<String, String> mapa = new HashMap<>();

        mapa.put("tipo", tipo);
        mapa.put("codigo", codigo);
        mapa.put("date", date);

        metadata.add(mapa);
    }
}
