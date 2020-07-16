package com.licitacao.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static String formatTimestamp(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return formatter.format(date);
    }
}
