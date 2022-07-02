import time
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait as wait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys

# Launch browser and navigate to NYCU home page
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))
driver.get("https://www.nycu.edu.tw/")

# Maximize the window
driver.maximize_window()

# Click 消息
news = wait(driver, 10).until(
    EC.element_to_be_clickable((driver.find_element_by_link_text("消息")))
).click()

# Click the 1st news
first_news = wait(driver, 10).until(
    EC.visibility_of_element_located((By.CSS_SELECTOR, ".eael-tabs-content ul li.su-post"))
).click()

# Print the title and content
title=driver.find_element_by_tag_name("h1")
print("============================================================")
print(title.text)
contents=driver.find_elements_by_tag_name("p")
for line in contents:
    print(line.text)
print("============================================================")

# Open a new tab and switch to it
driver.switch_to.new_window('tab')

# Navigate to google
driver.get("https://www.google.com")

# Input your student number and submit  
input = driver.find_element_by_xpath("//input[@name='q']") # find_element_by_name("q")
input.send_keys("0616086")
time.sleep(0.2)
input.send_keys(Keys.ENTER)

# Print the title of the 2nd result
results=driver.find_elements_by_tag_name("h3")
print("============================================================")
print(results[1].text)
print("============================================================")

# Close the browser
driver.close()
driver.quit()

