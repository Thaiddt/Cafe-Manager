/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafeteria.exception;

public class FormatVietNamException extends Exception{
	private static final long serialVersionUID = 1L;

    public FormatVietNamException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.getMessage();
    }
}
