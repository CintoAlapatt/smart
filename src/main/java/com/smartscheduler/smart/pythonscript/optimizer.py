import json
import sys

def calculate_travel_time(json_input):
#     print(f"Received input: {json_input}")
    data = json.loads(json_input)

    addresses = data['addresses']
    result = []
    time = 15  # Starting travel time

    for address in addresses:
        result.append({
            "id": address['id'],
            "travelTime": time
        })
        time += 10  # Increment travel time for next address

    return json.dumps(result)

if __name__ == "__main__":
    json_input = sys.stdin.read()
    print(calculate_travel_time(sys.argv[1]))
