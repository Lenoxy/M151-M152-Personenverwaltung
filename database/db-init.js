print('######## db-init.js executing now ########');
print('executing on ' + db.getName());

db.personEntity.drop();

db.createCollection("personEntity");

db.personEntity.insertMany(
    [
        {
            "_class": "ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity",
            "address": {
                "street": "Strasse",
                "number": "21",
                "zipcode": "6000",
                "city": "Luzern"
            },
            "email": "leo.scherer@bla.ch",
            "firstname": "Leo",
            "isAdmin": true,
            "lastname": "Scherer",
            "password": "hallovelo",
            "phonenumber": "0790000000",
            "position": "CIO",
            "username": "leo.scherer"
        },
        {
            "_class": "ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity",
            "address": {
                "street": "Strasse",
                "number": "21",
                "zipcode": "6000",
                "city": "Attinghausen"
            },
            "email": "daniel.polgar@gmail.com",
            "firstname": "Daniel",
            "isAdmin": true,
            "lastname": "Polgar",
            "password": null,
            "phonenumber": "0789876543",
            "position": "Sekretär",
            "username": "daniel.polgar"
        },
        {
            "_class": "ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity",
            "address": {
                "street": "Strasse",
                "number": "21",
                "zipcode": "6030",
                "city": "Ebikon"
            },
            "email": "darth.sidious@gmx.ch",
            "firstname": "Sheev",
            "isAdmin": false,
            "lastname": "Palpatine",
            "password": "hallovelo",
            "phonenumber": "0000000000",
            "position": "Chairman",
            "username": "darth.sidious"
        },
        {
            "_class": "ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity",
            "address": {
                "street": "Strasse",
                "number": "21",
                "zipcode": "6030",
                "city": "Ebikon"
            },
            "email": "anakin.skywalker@jedimail.gx",
            "firstname": "Anakin",
            "isAdmin": false,
            "lastname": "Skywalker",
            "password": "hallovelo",
            "phonenumber": "0000000000",
            "position": "Jedi Knight",
            "username": "anakin.skywalker"
        }
    ]
);
