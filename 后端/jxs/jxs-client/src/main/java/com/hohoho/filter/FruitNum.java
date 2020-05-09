package com.hohoho.filter;

public class FruitNum {
    public String isFruitNum(String series, String serieschild) {
        String fn = null;
        switch (series) {
            case "进口水果":
                switch (serieschild) {
                    case "南非水果":
                        fn = "A1";
                        break;
                    case "泰国水果":
                        fn = "A2";
                        break;
                    case "越南水果":
                        fn = "A3";
                        break;
                    case "菲律宾水果":
                        fn = "A4";
                        break;
                    case "新西兰水果":
                        fn = "A5";
                        break;
                }
                break;
            case "国产水果":
                switch (serieschild) {
                    case "时令水果":
                        fn = "B1";
                        break;
                    case "新品上市":
                        fn = "B2";
                        break;
                    case "推荐水果":
                        fn = "B3";
                        break;
                    case "国产热卖水果":
                        fn = "B4";
                        break;
                }
                break;
            case "水果礼盒":
                fn = "C1";
                break;
        }
        return fn;
    }
}
