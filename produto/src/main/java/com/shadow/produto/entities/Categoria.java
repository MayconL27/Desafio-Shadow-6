package com.shadow.produto.entities;
public enum Categoria {
    PADARIA(1),
    ACOUGUE(2),
    BEBIDAS(3),
    CEREAIS(4),
    HORTIFRUTI(5),
    OUTROS(6);
    private int code;
    private Categoria(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    public static Categoria valueOf(int code) {
        for(Categoria value : Categoria.values()) {
            if(value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Categoria code");
    }
//    public static Categoria valueOfName(String nome) {
//        for(Categoria value : Categoria.values()) {
//            if(value.name().equalsIgnoreCase(nome)) {
//                return value;
//            }
//        }
//        throw new IllegalArgumentException("Invalid Categoria code");
//    }
}
