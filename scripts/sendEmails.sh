cd calculadoraIMC

sudo apt-get update
sudo apt-get install -y mailutils

SUBJECT="Pipeline Completed ✅"
MESSAGE="Pipeline executado com sucesso! 🚀"

echo "$MESSAGE" | mail -s "$SUBJECT" "$EMAIL_TO"