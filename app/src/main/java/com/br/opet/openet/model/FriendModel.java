package com.br.opet.openet.model;

/*

GET - /friends/pending

Vai listar todos os pedidos de amizade pendendte
-----------------------------------------------------------
GET - /friends

Vai listar todos os pedidos de amizades que foram aceitos
-----------------------------------------------------------
POST - /friends/accept

body precisa passar o id do pedido de amizade
{
    id: "uuid"
}

Isso vai aceitar o pedido de amizade
-----------------------------------------------------------
POST - /friends/invite

body precisa passar o id do amiguinho
{
    friend_id: "uuid"
}

Vai enviar um pedido de amizade;
-----------------------------------------------------------
DELETE - /friends/reject

body precisa passar o id do pedido de amizade
{
	"id": "d9d0bfa3-c142-48ce-8bd9-3407274a4188"
}

Vai rejeitar o pedido de amizade

 */

public class FriendModel {

    String id;
    String name;
    String avatar;
    CourseModel course;

    public FriendModel() {}

    public FriendModel(String name, String image, CourseModel course) {
        this.name = name;
        this.image = image;
        this.course = course;
    }

    public FriendModel(String id, String name, String image, CourseModel course) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }
}
