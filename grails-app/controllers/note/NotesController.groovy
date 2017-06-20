package note

import grails.converters.JSON

import java.lang.reflect.InvocationTargetException

/**
 * Created by sramisetti on 6/8/2017.
 */
    class NotesController {

            def noteService

            def messageSource

            def index(){
                render "App is Running"
            }

            def list(){
                Integer userId = params.int('userId')
                List notes = noteService.listNotes(userId)
                render notes as JSON
            }

            def getById(){
                Integer userId = params.int('userId')
                Integer noteId = params.int('noteId')
                Note note = noteService.getById(userId, noteId)
                render note as JSON
            }

            def create(){
                Integer userId = params.int('userId')
                Map map = request.JSON
                Note note = noteService.createNote(map, userId)

                if(note!=null) {
                    if (note.hasErrors()) {
                        List errors = []
                        note.errors.allErrors.each { error ->
                            errors << messageSource.getMessage(error, Locale.default)
                        }
                        response.status = 400
                        render errors as JSON
                    } else {
                        render note as JSON
                    }
                }else{
                    render 'no user found to create note'
                }
            }


             def delete(){

                 Integer userId = params.int('userId')
                 Integer noteId = params.int('noteId')

                 boolean check = noteService.deleteNote(userId,noteId)

                 if(check == true){

                     render 'deleted'
                 }else{
                     render 'can not find'
                 }
             }

             def update(){

                 Integer userId = params.int('userId')
                 Integer noteId = params.int('noteId')
                 Map map = request.JSON

                 Note note = noteService.updateNote(userId, noteId, map)

                 if(note!=null){
                     if(note.hasErrors()){
                         List errors = []
                         note.errors.allErrors.each{ error->
                             errors << messageSource.getMessage(error, Locale.default)


                         }
                            render errors
                     }else{
                            render note as JSON
                     }
                 }else{
                        render 'no user found!'
                 }
             }


    }
