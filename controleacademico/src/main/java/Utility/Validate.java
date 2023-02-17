package Utility;

import java.util.InputMismatchException;

public class Validate {

    public static boolean cpf(String cpf) {
        if (cpf.length() != 11) {
            return false;
        }

        int resto;
        char[] n = cpf.toCharArray();

        for (int i = 0; i < 11; i++) {
            if (i == 10) {
                return false;
            }
            if (n[i] != n[i + 1]) {
                break;
            }
        }

        int soma = 0;
        for (int peso = 10, pos = 0; pos < 9; peso--, pos++) {
            soma += ((int) n[pos] - 48) * peso;
        }

        resto = (soma * 10) % 11;
        if (resto == 10) {
            resto = 0;
        }
        if (resto != ((int) n[9] - 48)) {
            return false;
        }

        soma = 0;
        for (int peso = 11, pos = 0; pos < 10; peso--, pos++) {
            soma += ((int) n[pos] - 48) * peso;
        }

        resto = (soma * 10) % 11;
        if (resto == 10) {
            resto = 0;
        }
        if (resto != ((int) n[10] - 48)) {
            return false;
        }

        // System.out.println("CPF Valido");
        return true;
    }

    public static boolean cnpj(String cnpj) {
        if (cnpj.length() != 14) {
            return false;
        }

        int resto;
        char[] n = cnpj.toCharArray();

        int soma = 0;
        for (int peso = 5, pos = 0; pos < 12; peso--, pos++) {
            if (peso == 1) {
                peso = 9;
            }
            soma += ((int) n[pos] - 48) * peso;
        }

        resto = soma % 11;
        if (resto < 2) {
            resto = 0;
        } else {
            resto = 11 - resto;
        }

        if (resto != ((int) n[12] - 48)) {
            return false;
        }

        soma = 0;
        for (int peso = 6, pos = 0; pos < 13; peso--, pos++) {
            if (peso == 1) {
                peso = 9;
            }
            soma += ((int) n[pos] - 48) * peso;
        }

        resto = soma % 11;
        if (resto < 2) {
            resto = 0;
        } else {
            resto = 11 - resto;
        }

        if (resto != ((int) n[13] - 48)) {
            return false;
        }

        //System.out.println("CNPJ Valido");
        return true;
    }

    public static boolean isCpf(String cpf) {
        if (cpf.equals("00000000000") || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999")
                || cpf.length() != 11) {
            return false;
        } else {
            char dig;
            int soma = 0, i, r, num = 0, peso = 10;
            try {
                for (i = 0; i < 9; i++) {
                    num = (int) (cpf.charAt(i) - 48);
                    soma += (num * peso);
                    peso--;
                }
                r = 11 - (soma % 11);
                if (r >= 10) {
                    dig = '0';
                } else {
                    dig = (char) (r + 48);
                }
                if (dig != cpf.charAt(9)) {
                    return false;
                } else { ///////////
                    peso = 11;
                    soma = 0;

                    for (i = 0; i < 10; i++) {
                        num = (int) (cpf.charAt(i) - 48);
                        soma += (num * peso);
                        peso--;
                    }
                    r = 11 - (soma % 11);
                    if (r >= 10) {
                        dig = '0';
                    } else {
                        dig = (char) (r + 48);
                    }
                    if (dig != cpf.charAt(10)) {
                        return false;
                    } else {
                        return true;
                    }
                }
            } catch (InputMismatchException erro) {
                return false;
            }
        }
    };
}
