package com.javarush.task.task22.task2208;

import java.util.*;

/* 
Формируем WHERE
Формируем WHERE
Сформируй часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.

Пример:
{name=Ivanov, country=Ukraine, city=Kiev, age=null}

Результат:
name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'


Требования:
1. Метод getQuery должен принимать один параметр типа Map.
2. Метод getQuery должен иметь тип возвращаемого значения String.
3. Метод getQuery должен быть статическим.
4. Метод getQuery должен возвращать строку сформированную по правилам описанным в условии задачи.
*/

public class Solution {
    public static void main(String[] args) {
        Map<String,String> ivanov = new HashMap<>();
        ivanov.put("name","Ivanov");
        ivanov.put("country","Ukraine");
        ivanov.put("city","Kiev");
        ivanov.put("age",null);

        System.out.println(getQuery(ivanov));
    }

    public static String getQuery(Map<String, String> params) {
        List<String> some = new ArrayList<>();
        StringBuilder where = new StringBuilder();
        for(Map.Entry<String,String> pair: params.entrySet()){
            if(pair.getKey() != null && pair.getValue() != null){
                some.add(pair.getKey() + " = \'" + pair.getValue() + "\'");
            }

        }
        for (int i = 0; i < some.size(); i++){
            where.append(some.get(i));
            if (i != some.size() - 1){ where.append(" and ");}
        }
        return where.toString();
    }
}
