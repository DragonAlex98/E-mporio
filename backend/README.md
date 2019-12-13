backend

Esempi con Postman

--------------------------------------------------------

Esempio di registrazione
Metodo          URL
POST            http://localhost:8000/auth/signup

Body (JSON)
{
	"username": "miouser",
	"password": "miapassword"
}

STATO
201 se successo
409 se già esiste

--------------------------------------------------------

Esempio di login
Metodo          URL
POST            http://localhost:8000/auth/signin

Body (JSON)
{
	"username": "miouser",
	"password": "miapassword"
}

STATO
200 se successo
403 se errore

RISPOSTA
{
    "username": "miouser",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaW91c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTU3NjI1ODI1NiwiZXhwIjoxNTc2MjY1NDU2fQ.NvATNhfIXAVKuhMuTv3lXHq1ytXoHRMpxckvI3oLZ9I"
}

--------------------------------------------------------

Esempio utilizzo token
Metodo          URL
GET            http://localhost:8000/me

Body
niente

AUTHORIZATION
Bearer Token
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaW91c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTU3NjI1ODI1NiwiZXhwIjoxNTc2MjY1NDU2fQ.NvATNhfIXAVKuhMuTv3lXHq1ytXoHRMpxckvI3oLZ9I

STATO
200 se successo

RISPOSTA
{
    "roles": [
        "ROLE_USER"
    ],
    "username": "miouser"
}