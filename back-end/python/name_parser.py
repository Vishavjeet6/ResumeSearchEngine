import spacy
from spacy.matcher import Matcher

# load pre-trained model
nlp = spacy.load('en_core_web_sm')

# initialize matcher with a vocab
matcher = Matcher(nlp.vocab)

def extract_name(resume_text):
    nlp_text = nlp(resume_text)
    
    # First name and Last name are always Proper Nouns
    pattern1 = [{'POS': 'PROPN'}]
    pattern2 = [{'POS': 'PROPN'}, {'POS': 'PROPN'}]
    matcher.add('NAME', None, pattern1, pattern2)
    
    matches = matcher(nlp_text)
    
    # if lastname is not present
    # this will work only for firstname || lastname 
    oldname = ''
    for match_id, start, end in matches:
        span = nlp_text[start:end]
        if oldname != '': 
            if oldname in span.text:
                return span.text
            else:
                return oldname
        else:
            oldname = span.text


 