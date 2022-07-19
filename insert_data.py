import psycopg2

conn = psycopg2.connect(
    host="localhost",
    database="postgres",
    user="postgres",
    password="postgres"
)

def insert_hive(cur):
    cur.execute("Insert into hive(id, name, system) values (1, 'Peter','Zander');COMMIT;") 

def insert_record(cur):
    cur.execute("Insert into record(name, temperature, weather, task) values('dings', 5.5, 'clear', 'nichts');COMMIT;")

with conn.cursor() as cur:
    cur.execute("Select * from hive;")
    print(cur.fetchall())
    insert_hive(cur)
    cur.execute("Select * from hive;")
    print(cur.fetchall())

    cur.execute("Select * from record;")
    print(cur.fetchall())
    insert_record(cur)
    cur.execute("Select * from record;")
    print(cur.fetchall())
