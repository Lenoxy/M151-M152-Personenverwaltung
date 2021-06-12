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
            "admin": true,
            "lastname": "Scherer",
            "password": "243ab44b06e2ed10107bbf71c3a31f0ba3169d1e12fd9e5826fdfe4b771991ed4009a3cdcea46c22",
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
            "admin": true,
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
            "admin": false,
            "lastname": "Palpatine",
            "password": null,
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
            "admin": false,
            "lastname": "Skywalker",
            "password": "23ed036b4bcfdc3c788ef431ca79fecce1597752c0738fac0f894e9dc4504699c481d697a13a7b6e",
            "phonenumber": "0000000000",
            "position": "Jedi Knight",
            "username": "anakin.skywalker"
        }
    ]
);

db.personEntity.createIndex({ email: 1 }, { unique: true })
db.personEntity.createIndex({ username: 1 }, { unique: true })

print("###### DB SCRIPT DONE #####")
