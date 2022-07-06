use CelestialDiaryDB
const resComp = db.Company.insertOne({
    "name": "supercompany",
    "email": "super-company@hotmail.fr",
    "password": "superCompanyPassword",
    "description": "A SuperCompany where everybody should go",
    "phone": "+33 6 07 27 15 65",
    "creationDate": "$$NOW"
})
db.Employee.insertOne({
    "email": "guil.rako@hotmail.fr",
    "password": "Callimard94500$",
    "firstName": "Guillaume",
    "lastName": "Rakotomalala",
    "personGender": "MALE",
    "phone": "+33 6 07 27 14 40",
    "active": true,
    "companySummary": {"companyEmail": "super-company@hotmail.fr", "companyName": "supercompany", "company": resComp.insertedId},
    "creationDate": "$$NOW"
})