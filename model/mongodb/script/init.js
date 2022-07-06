use CelestialDiaryDB

db.createCollection("Company")
db.createCollection("Employee")
db.createCollection("Establishment")
db.createCollection("Role")

db.Company.createIndex({email: 1}, {name: "company_email_uq_idx", unique: true})
db.Company.createIndex({name: 1}, {name: "company_name_uq_idx", unique: true})
db.Company.createIndex({phone: 1}, {name: "company_phone_idx"})

db.Employee.createIndex({'companySummary.company': 1, email: 1}, {name: "employee_company_email_uq_idx", unique: true})

db.Establishment.createIndex({'companySummary.company': 1, name: 1}, {name: "establishment_company_name_uq_idx", unique: true})

db.Role.createIndex({'companySummary.company': 1, name: 1}, {name: "role_company_name_uq_idx", unique: true})