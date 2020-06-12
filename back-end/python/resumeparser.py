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






# testing
import os
import glob

os.chdir("C:/Users/visha/Desktop/research/attempt2")

pdfs = glob.glob("PDF/*.pdf*")

for pdf in pdfs:
    text = ''
    for page in extract_text_from_pdf(pdf):
        text += ' ' + page
    # print(text)
    print(extract_mobile_number(text))
    print(extract_email(text))
    print(extract_skills(text))
    print(extract_name(text))
    print(extract_education(text))
    print("******************************")
    print("******************************")
    print("******************************")




# text = ''
# for page in extract_text_from_pdf(pdfs[9]):
#     text += ' ' + page
# print(text)
# print(extract_mobile_number(text))
# print(extract_email(text))
# print(extract_skills(text))
# print(extract_name(text))
# print(extract_education(text))
# print("******************************")
# print("******************************")
# print("******************************")