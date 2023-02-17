/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBConfig.DBConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import Repository.*;

/**
 *
 * @author Emanuelle Scheifer
 * 
 */
public class DAO {
    private static final AnnotationConfigApplicationContext ctx
            = new AnnotationConfigApplicationContext(DBConfig.class);
    
    public static ProfessorRepository professorRepository = ctx.getBean(ProfessorRepository.class);
}
