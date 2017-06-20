

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

//        "/users"(controller: 'user'){
//            action = [ POST: 'create', GET:'list']
//        }

        "/users/$userId"(controller: 'user'){
            action = [ PUT:"update", DELETE: 'delete', GET: 'getById']
        }

        "/users/$userId/notes"(controller: 'notes') {
            action = [ GET: 'list', POST: 'create' ]
        }

        "/users/$userId/notes/$noteId"(controller: 'notes'){
            action = [DELETE: 'delete', PUT: 'update', GET: 'getById']
        }

        "/"(controller: 'notes', action: 'index')

        //  "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
