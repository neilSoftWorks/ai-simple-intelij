import json
import os

def update_files_from_json(json_file_path):
    """
    Updates files in the local project directory based on a JSON file.

    Args:
        json_file_path (str): Path to the JSON file.
    """
    try:
        with open(json_file_path, 'r', encoding='utf-8') as f:
            files = json.load(f)

        for file_data in files:
            path = file_data['path']
            content = file_data['content']

            # Create directories if they don't exist
            directory = os.path.dirname(path)
            if directory and not os.path.exists(directory):
                os.makedirs(directory)

            # Create or update the file
            with open(path, 'w', encoding='utf-8') as file:
                file.write(content)

            print(f"Updated file: {path}")

        print("Files updated successfully.")

    except FileNotFoundError:
        print(f"Error: JSON file not found at {json_file_path}")
    except json.JSONDecodeError:
        print("Error: Invalid JSON format in {json_file_path}")
    except Exception as e:
        print(f"An error occurred: {e}")

if __name__ == "__main__":
    update_files_from_json("project_files.json")