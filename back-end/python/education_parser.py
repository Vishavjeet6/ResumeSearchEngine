import re
import spacy
from nltk.corpus import stopwords

# load pre-trained model
nlp = spacy.load('en_core_web_sm')

# Grad all general stop words
STOPWORDS = set(stopwords.words('english'))

# Education Degrees
# EDUCATION = ['BED', 'MS', 'BCOM', 'SSLC', 'MTECH', 'TGT', 'HSC', 'PUC', 
# 'AICTE', 'ISC', 'MSC', 'BE', 'MCA', 'BSC', 'XII', 'BHMS', 'BTECH', 'CBSE', 
# 'ICSE', 'BBA', 'CV', 'SSC', 'LLB', 'ME', 'BCA', 'BA', 'MD', 'PHD', 'LLM', 
# 'DP', 'PCS', 'CS', 'IAS', 'NIIT', 'X', 'GPA', 'BS', 'BACHELOR', 'PGDM', 'NDA', 
# 'MBA', 'AMIE', 'PGDCA']
EDUCATION = ['BED', 'MS', 'BCOM', 'SSLC', 'MTECH', 'TGT', 'HSC', 'PUC', 'AICTE', 
'ISC', 'MSC', 'BE', 'MCA', 'BSC', 'XII', 'BHMS', 'BTECH', 'CBSE', 'ICSE', 'BBA', 
'CV', 'SSC', 'LLB', 'ME', 'BCA', 'BA', 'MD', 'PHD', 'LLM', 'DP', 'PCS', 'CS', 'IAS', 
'NIIT', 'X', 'GPA', 'BS', 'PGDM', 'NDA', 'MBA', 'AMIE', 'PGDCA', 'BACHELOR', 'MASTER',
'BACHELORS', 'MASTERS']


def extract_education(resume_text):
    nlp_text = nlp(resume_text)

    # Sentence Tokenizer
    nlp_text = [sent.string.strip() for sent in nlp_text.sents]

    edu = {}
    # Extract education degree
    for index, text in enumerate(nlp_text):
    
        for tex in text.split():
            # Replace all special symbols
            tex = re.sub(r'[?|$|.|!|,]', r'', tex)
            if tex.upper() in EDUCATION and tex not in STOPWORDS:

                edu[tex] = text + nlp_text[index + 1]


    # Extract year
    education = []
    for key in edu.keys():
        d={}
        year = re.search(re.compile(r'(((20|19)(\d{2})))'), edu[key])
        if year:
            d["degree"] = key
            d["fulleducation"] = edu[key]
            d["year"] = ''.join(year[0])
            # education.append((key, ''.join(year[0])))
            # education.append(({"degree": key},{"fulleducation":edu[key]}, {"year":''.join(year[0])}))       
        else:
            d["degree"] = key
            d["fulleducation"] = edu[key]
            d["year"] = ""
            # education.append(key)
            # education.append(({"degree": key},{"fulleducation":edu[key]}))
        education.append(d)
    return education