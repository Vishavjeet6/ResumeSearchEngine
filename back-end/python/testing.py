import mysql.connector
from mysql.connector import Error
import sys
import os
import glob
import io

from mobile_parser import extract_mobile_number
from email_parser import extract_email
from skill_parser import extract_skills
from name_parser import extract_name
from education_parser import extract_education

from pdfminer.converter import TextConverter
from pdfminer.pdfinterp import PDFPageInterpreter
from pdfminer.pdfinterp import PDFResourceManager
from pdfminer.layout import LAParams
from pdfminer.pdfpage import PDFPage

os.chdir("C:/Users/visha/Desktop/research/attempt2")

def extract_text_from_pdf(pdf_path):
    with open(pdf_path, 'rb') as fh:

        # iterate through all pages
        for page in PDFPage.get_pages(fh, caching=True, check_extractable=True):
  
            # resource manager
            resource_manager = PDFResourceManager()
  
            # file handle 
            fake_file_handle = io.StringIO()
  
            # text converter object
            converter = TextConverter(
                resource_manager,
                fake_file_handle,
                laparams=LAParams()
            )
  
            # page interprter
            page_interpreter = PDFPageInterpreter(
                resource_manager,
                converter
            )
            page_interpreter.process_page(page)
  
            # extract text
            text = fake_file_handle.getvalue()
            yield text
  
            # close open handle
            converter.close()
            fake_file_handle.close()

def home(file_Id):
    try:
        conn = mysql.connector.connect(host='localhost',
                            database='elastic',
                            user='root',
                            password='root')
        if conn.is_connected():
            with open("Output.pdf", "wb") as output_file:
                cursor = conn.cursor()
                cursor.execute("select fileByte from filemodel where fileId = %s", (file_Id, ))
                record = cursor.fetchone()
                output_file.write(record[0])
        
    except Error as e :
        print ("Print your error msg", e)
    finally:
        #closing database connection.
        if(conn.is_connected()):
            cursor.close()
            conn.close()

arg = sys.argv[1]
home(arg)
# home(5)

pdf = glob.glob("Output.pdf")

text = ''
output = {}
for page in extract_text_from_pdf(pdf[0]):
    text += ' ' + page
output['phoneNumber'] = extract_mobile_number(text)
output['email'] = extract_email(text)
output['name'] = extract_name(text)
output['skills'] = extract_skills(text)
output['education'] = extract_education(text)

# print(text)
# print(extract_mobile_number(text))
# print(extract_email(text))
# print(extract_skills(text))
# print(extract_name(text))
# print(extract_education(text))
print(output)
