import serial
import json
import requests

seri_port = "COM3"  # Select related COM port


def login(username, password):
    login_url = "http://localhost:8080/api/auth/token"
    login_data = {
        "username": username,
        "password": password
    }

    try:
        response = requests.post(login_url, json=login_data)
        response.raise_for_status()  # Raise an HTTPError for bad responses (4xx or 5xx)

        token = response.json().get("jwt")
        if token:
            print("Login successful. JWT token obtained.")
            return token
        else:
            print("Error: JWT token not found in the response.")
            return None

    except requests.exceptions.RequestException as e:
        print(f"Error during login: {e}")
        return None


def rearrange(liste):
    temiz_liste = []
    for i in range(len(liste)):
        temp = liste[i][2:]

        data = str(temp[:-5])

        data = data.strip()

        if len(data) > 0:
            print("data: ", data)
            temiz_liste.append(data)

    return temiz_liste


def yaz(liste):
    dosya = open("veri.txt", mode='a')
    for i in range(len(liste)):
        dosya.write(liste[i] + '\n')
    dosya.close()


def jsonData(data_list):

    # Mapping raw data to JSON keys
    data_mapping = {
        "SuSeviyesi": "waterLevel",
        "Isik": "lightDensity",
        "Hava nem": "humidityWeather",
        "sicaklik": "temperature",
        "torak nem": "humiditySoil"
    }

    # Initialize an empty dictionary to store the mapped data
    mapped_data = {}

    # Iterate through the raw data list and map values to keys
    for i in range(0, len(data_list), 2):
        key = data_list[i].strip()
        value = float(data_list[i + 1].strip())

        # Check if the key is in the mapping dictionary
        if key in data_mapping:
            mapped_key = data_mapping[key]
            mapped_data[mapped_key] = value

    # Convert the mapped data to JSON format
    json_data = json.dumps(mapped_data, indent=4)
    print("====>> MAPPED:", mapped_data)
    return mapped_data


def post_data(json_data, token):

    # Define your server URL
    server_url = "http://localhost:8080/api/sensorData"

    headers = {
        "Authorization": f"Bearer {token}"  # Use the JWT token as Bearer token
    }

    try:
        # Send a POST request to the server with the JWT token in the headers
        response = requests.post(server_url, json=json_data, headers=headers)

        # Check if the request was successful (status code 200)
        if response.status_code == 201:
            print("Data successfully sent to the server!")
        else:
            print(f"Error: {response.status_code} - {response.text}")

    except requests.exceptions.RequestException as e:
        print(f"Error: {e}")


try:
    arduino = serial.Serial(seri_port, timeout=1)
except Exception as e:
    print(f'Hata: {e}')
    arduino = None  # Set arduino to None if there is an exception

raw_veri = []
sayac = 0

# Check if arduino is defined before using it
jwt_token = login("admin", "admin.A1")


while arduino:
    while sayac < 14:
        raw_veri.append(str(arduino.readline()))
        sayac += 1
        print(f"line {sayac}: {raw_veri}")

    cleandata = rearrange(raw_veri)
    # print("cleaned: ", cleandata)
    # yaz(cleandata)
    json_data = jsonData(cleandata)
    print(type(json_data))
    print(json_data)
    post_data(json_data, jwt_token)

    raw_veri = []
    sayac = 0
else:
    print("Port açılamadı, işlem iptal ediliyor.")
