#!/bin/sh

set -e

# Wait for Vault to be ready
until vault status; do
  echo "Vault is not ready yet. Please wait.."
  sleep 1
done

echo "vault ready"
PRESCRIPTION_SECRETS_FILE="/init/prescription_secrets.json"
MEDICATION_SECRETS_FILE="/init/medication_secrets.json"

# Load secrets from JSON file
if [ -f "$PRESCRIPTION_SECRETS_FILE" ]; then
  vault secrets enable -path=prescription-service  kv
  vault kv put prescription-service/config @$PRESCRIPTION_SECRETS_FILE
else
  echo "ERROR: Secrets file '$PRESCRIPTION_SECRETS_FILE' not found!"
    exit 1
fi

if [ -f "$MEDICATION_SECRETS_FILE" ]; then
vault secrets enable -path=medication-service   kv
vault kv put medication-service/config @$MEDICATION_SECRETS_FILE
else
  echo "ERROR: Secrets file 'MEDICATION_SECRETS_FILE' not found!"
    exit 1
fi

tail -f /dev/null