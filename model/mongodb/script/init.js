use CelestialDiaryDB

db.createCollection("Company")
db.Company.createIndex({email: 1}, {name: "company_email_uq_idx", unique: true})
db.Company.createIndex({name: 1}, {name: "company_name_uq_idx", unique: true})
db.Company.createIndex({phone: 1}, {name: "company_phone_idx"})

db.createCollection("Employee")
db.Employee.createIndex({'company': 1, email: 1}, {name: "employee_company_email_uq_idx", unique: true})

db.createCollection("EmployeeWorkingHours")
db.EmployeeWorkingHours.createIndex({'employee': 1, 'establishment': 1, year: 1, weekNumber: 1}, {name: "employee_working_hours_primary_key_uq", unique: true})

db.createCollection("Client")
db.Client.createIndex({'company': 1, email: 1}, {name: "client_company_email_uq_idx", unique: true})

db.createCollection("Establishment")
db.Establishment.createIndex({'company': 1, name: 1}, {name: "establishment_company_name_uq_idx", unique: true})

db.createCollection("Role")
db.Role.createIndex({'company': 1, name: 1}, {name: "role_company_name_uq_idx", unique: true})

db.createCollection("Product")
db.Product.createIndex({'company': 1, name: 1}, {name: "product_company_name_uq_idx", unique: true})

db.createCollection("Prestation")
db.Prestation.createIndex({'company': 1, name: 1}, {name: "prestation_company_name_uq_idx", unique: true})

db.createCollection("Bundle")
db.Bundle.createIndex({'company': 1, name: 1}, {name: "bundle_company_name_uq_idx", unique: true})

db.createCollection("Equipment")
db.Equipment.createIndex({'company': 1, name: 1}, {name: "equipment_company_name_uq_idx", unique: true})