#!/bin/bash

# --- CONFIGURATION ---
DEFINITIONS_FILE="$1"
PROJECT_DIR="$2"
SCRIPT_DIR="$( cd "$( dirname "$0" )" && pwd )"
echo $PWD

# --- VALIDATION ---
if [[ -z "$DEFINITIONS_FILE" || -z "$PROJECT_DIR" ]]; then
  echo "Usage: $0 <path_to_definitions_file> <path_to_project_directory>"
  exit 1
fi

# --- CHOOSE SEARCH TOOL ---
# Check if 'rg' is installed and executable
if command -v rg &> /dev/null; then
  echo "🚀 Ripgrep (rg) found. Using it for faster searching."
  # rg is recursive by default. -q for quiet, -w for whole word.
  SEARCH_CMD=("rg" "-qw")
else
  echo "Standard grep will be used. For faster results, install ripgrep."
  SEARCH_CMD=("grep" "-rqw")
fi
echo "---"

# --- CORE LOGIC ---
awk -f $SCRIPT_DIR/query-constants.awk "$DEFINITIONS_FILE" | while read -r symbol; do
  # Use the selected search command stored in the SEARCH_CMD array
  if ! "${SEARCH_CMD[@]}" "$symbol" "$PROJECT_DIR"; then
    echo "⚠️  Symbol '$symbol' may be unused."
  fi
done

echo "---"
echo "✅ Check complete."
